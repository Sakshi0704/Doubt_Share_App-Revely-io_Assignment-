import React, { useState, useEffect } from "react";
import "../CSS/StudentDataPage.css";
const StudentDataPage = () => {
    const [doubts, setDoubts] = useState([]);

    // Function to handle logout
    const handleLogout = () => {
        // Clear the token from local storage
        localStorage.removeItem("token");

        // Redirect to the login page or perform other logout actions
        window.location.href = "/login"; // Replace with your login page route
    };

    useEffect(() => {
        // Fetch data from the provided URL using the stored token
        const token = localStorage.getItem("token");

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
            // Handle case where there is no token (user is not authenticated)
            console.error("User not authenticated. Redirect to login page.");

            // Redirect to the login page or perform other actions
            // window.location.href = "/login"; // Replace with your login page route
        }
    }, []); // The empty dependency array ensures that this effect runs once when the component mounts

    return (
        <div>
            <h1>Hiii kaise ho?</h1>
            <button onClick={handleLogout}>Logout</button>
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
                    </tr>
                </thead>
                <tbody>
                    {doubts.map((doubt) => (
                        <tr
                            key={doubt.doubtRequestId}

                        >
                            <td>{doubt.doubtRequestId}</td>
                            <td>{doubt.doubtSubject}</td>
                            <td>{doubt.doubtTitle}</td>
                            <td>{doubt.doubtDescription}</td>
                            <td style={{ color: doubt.doubtStatus === "PANDDING" ? "red" : "green" }}>{doubt.doubtStatus}</td>
                            <td>{doubt.resolveTime}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default StudentDataPage;
