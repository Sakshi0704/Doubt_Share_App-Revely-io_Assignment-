import React, { useState } from "react";
import "../CSS/Signup.css";
const StudentSignup = () => {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userLanguage, setUserLanguage] = useState("");
  const [class_grade, setClassGrade] = useState("");

  const handleUserNameChange = (e) => {
    setUserName(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleUserLanguageChange = (e) => {
    setUserLanguage(e.target.value);
  };

  const handleClassGradeChange = (e) => {
    console.log("Class Grade Changed:", e.target.value);
    setClassGrade(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const studentData = {
      userName,
      email,
      password,
      userLanguage,
      class_grade,
    };
    console.log("Student Signup Data:", studentData);
    fetch("http://localhost:8085/doubt-sharing-app/auth/register-student", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(studentData),
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(data);
        window.alert("Signup successful!");
        window.location.href = "/login"; 
      })
      .catch((error) => {
        console.log("Error:", error);
      });
  };

  return (
    <div>
      <h1>Student Signup</h1>
      <form onSubmit={handleSubmit}>
        <label>
          User Name:
          <input
            type="text"
            value={userName}
            onChange={handleUserNameChange}
            required
          />
        </label>
        <br />
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={handleEmailChange}
            required
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={handlePasswordChange}
            required
          />
        </label>
        <br />
        <label>
          User Language:
          <input
            type="text"
            value={userLanguage}
            onChange={handleUserLanguageChange}
            required
          />
        </label>
        <br />
        <label>
          Class Grade:
          <input
            type="text"
            value={class_grade}
            onChange={handleClassGradeChange}
            required
          />
        </label>
        <br />
        <button id="subBtn" type="submit">
          Sign Up
        </button>
        <br />
      </form>
    </div>
  );
};

export default StudentSignup;
