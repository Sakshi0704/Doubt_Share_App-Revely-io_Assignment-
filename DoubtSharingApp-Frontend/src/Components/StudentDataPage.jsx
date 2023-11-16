import React, { useState, useEffect } from "react";
import "../CSS/StudentDataPage.css";

const StudentDataPage = () => {
    const [doubts, setDoubts] = useState([]);
    const [newDoubt, setNewDoubt] = useState({
        doubtSubject: "",
        doubtTitle: "",
        doubtDescription: "",
    });
   
 

    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.href = "/login";
    };

    const checkTutorDoubt = (doubtId) => {
        const token = localStorage.getItem("token");

        fetch(`http://localhost:8085/doubt-sharing-app/student/assigne-doubt-to-live-tutor?doubtId=${doubtId}`, {
            method: "POST",
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
            })
            .then((tutorData) => {
                // Update the doubts table based on tutor's data
                console.log(tutorData);
                updateDoubtInTable(tutorData);
            })
            .catch((error) => console.error("Error checking tutor's doubt:", error));
    };

    const updateDoubtInTable = (updatedDoubt) => {
        // Update the doubts table based on the updated doubt
        setDoubts((prevDoubts) => {
            // Remove the old doubt (if exists) and add the updated one
            const filteredDoubts = prevDoubts.filter((doubt) => doubt.doubtRequestId !== updatedDoubt.doubtRequestId);
            return [updatedDoubt, ...filteredDoubts];
        });
    };

    const handleAddDoubt = () => {
        const token = localStorage.getItem("token");

        if (token) {
            fetch("http://localhost:8085/doubt-sharing-app/student/add-doubt-request", {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(newDoubt),
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then((data) => {
                    // If doubt added successfully, check if tutor has received the doubt
                    const doubtId = data.doubtRequestId;

                    if (doubtId) {
                        // Update the state with the new doubt
                        setDoubts((prevDoubts) => [data, ...prevDoubts]);

                        // Check if tutor has received the doubt
                        checkTutorDoubt(doubtId);
                    } else {
                        console.error("Doubt ID not available in the response.");
                    }
                })
                .catch((error) => console.error("Error adding doubt:", error));
        } else {
            console.error("User not authenticated. Redirect to login page.");
        }
    };

    useEffect(() => {
        const token = localStorage.getItem("token");
       setInterval(()=>{

        if (token) {
            fetch("http://localhost:8085/doubt-sharing-app/student/all-doubts/history", {
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
                .then((data) => setDoubts(data))
                .catch((error) => console.error("Error fetching data:", error));
        } else {
            console.error("User not authenticated. Redirect to login page.");
        }
       },5000)
       
    }, []);

    return (
        <div>
            {/* Add Doubt Form */}
            <div>
                <h2>Add Doubt</h2>
                <label>
                    Subject:
                    <input
                        type="text"
                        value={newDoubt.doubtSubject}
                        onChange={(e) => setNewDoubt({ ...newDoubt, doubtSubject: e.target.value })}
                    />
                </label>
                <br />
                <label>
                    Title:
                    <input
                        type="text"
                        value={newDoubt.doubtTitle}
                        onChange={(e) => setNewDoubt({ ...newDoubt, doubtTitle: e.target.value })}
                    />
                </label>
                <br />
                <label>
                    Description:
                    <textarea
                        value={newDoubt.doubtDescription}
                        onChange={(e) => setNewDoubt({ ...newDoubt, doubtDescription: e.target.value })}
                    />
                </label>
                <br />
                <br />
                <button onClick={handleAddDoubt}>Add Doubt</button>
            </div>
            <br />
            <br />
            <br />
            <h2>Doubt History</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Doubt ID</th>
                        <th>Subject</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Resolve Time</th>
                        <th>Doubt Resolve Description</th>
                    </tr>
                </thead>
                <tbody>
                    {doubts.map((doubt) => (
                        <tr key={doubt.doubtRequestId}>
                            <td>{doubt.doubtRequestId}</td>
                            <td>{doubt.doubtSubject}</td>
                            <td>{doubt.doubtTitle}</td>
                            <td>{doubt.doubtDescription}</td>
                            <td style={{ color: doubt.doubtStatus === "PENDING" ? "red" : (doubt.doubtStatus === "RESOLVED" ? "green" : "blue") }}>{doubt.doubtStatus}</td>

                            <td>{doubt.resolveTime}</td>
                            <td>{doubt.doubtResolveDescription === null ? "Please visit after sometime" : `${doubt.doubtResolveDescription}`}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <br />
            <br />
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
};

export default StudentDataPage;
