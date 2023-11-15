import React, { useState, useEffect } from "react";
import "../CSS/Signup.css";

const TutorDataPage = () => {
  const [doubts, setDoubts] = useState([]);
  const [resolveForm, setResolveForm] = useState({
    doubtRequestId: null,
    doubtResolveDescription: "",
  });
  const token = localStorage.getItem("token");

  const markAsResolve = (doubtRequestId) => {
    setResolveForm({
      doubtRequestId: doubtRequestId,
      doubtResolveDescription: "",
    });
  };

  const handleResolveFormChange = (e) => {
    const { name, value } = e.target;
    console.log(value);
    setResolveForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleResolveSubmit = () => {
    const apiUrl = `http://localhost:8085/doubt-sharing-app/tutor/doubt-solve/${resolveForm.doubtRequestId}?solvedDesc=${resolveForm.doubtResolveDescription}`;

    fetch(apiUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      }
      
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log("Doubt resolved successfully:", data);

        // Update local state
        setDoubts((prevDoubts) =>
          prevDoubts.map((doubt) =>
            doubt.doubtRequestId === resolveForm.doubtRequestId
              ? {
                  ...doubt,
                  doubtStatus: "Resolved",
                  doubtResolveDescription: resolveForm.doubtResolveDescription,
                }
              : doubt
          )
        );

        // Reset form
        setResolveForm({
          doubtRequestId: null,
          doubtResolveDescription: "",
        });
      })
      .catch((error) => {
        console.log("Error resolving doubt:", error);

        // Log additional details if available
        if (error.response) {
          console.error("Response details:", error.response);
        }
      });
  };

  useEffect(() => {
    fetch("http://localhost:8085/doubt-sharing-app/tutor/pending-doubts", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log("Fetched data:", data);
        setDoubts(data);
      })
      .catch((error) => console.error("Error fetching data:", error));
  }, [token]);

  return (
    <div>
      <h1>Doubt List</h1>
      <table>
        <thead>
          <tr>
            <th>Doubt ID</th>
            <th>Subject</th>
            <th>Title</th>
            <th>Description</th>
            <th>Request Time</th>
            <th>Status</th>
            <th>Resolve Description</th>
            <th>Resolve Time</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {doubts.map((doubt) => (
            <tr key={doubt.doubtRequestId}>
              <td>{doubt.doubtRequestId}</td>
              <td>{doubt.doubtSubject}</td>
              <td>{doubt.doubtTitle}</td>
              <td>{doubt.doubtDescription}</td>
              <td>{doubt.requestTime}</td>
              <td>{doubt.doubtStatus}</td>
              <td>{doubt.doubtResolveDescription}</td>
              <td>{doubt.reloveTime}</td>
              <td>
                {doubt.doubtStatus === "ASSIGNED" && (
                  <>
                    <button onClick={() => markAsResolve(doubt.doubtRequestId)}>
                      Mark as Resolve
                    </button>
                    {resolveForm.doubtRequestId === doubt.doubtRequestId && (
                      <div>
                        <input
                          type="text"
                          name="doubtResolveDescription"
                          value={resolveForm.doubtResolveDescription}
                          onChange={handleResolveFormChange}
                          placeholder="Enter resolve description"
                        />
                        <button onClick={handleResolveSubmit}>Submit</button>
                      </div>
                    )}
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TutorDataPage;
