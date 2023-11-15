
import React, { useState } from "react";
import "../CSS/Signup.css";

const TutorSignup = () => {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userLanguage, setUserLanguage] = useState("");
  const [min_grade, setMinGrade] = useState("");
  const [max_grade, setMaxGrade] = useState("");
  const [subjectExpertise, setSubjectExpertise] = useState("");

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

  const handleMinGradeChange = (e) => {
    let min = e.target.value
    console.log(min);
    setMinGrade(min);
  };

  const handleMaxGradeChange = (e) => {
    let max = e.target.value
    console.log(max);
    setMaxGrade(max);
  };

  const handleSubjectExpertiseChange = (e) => {
    setSubjectExpertise(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const tutorData = {
      userName,
      email,
      password,
      userLanguage,
      min_grade: min_grade.toUpperCase(),
      max_grade: max_grade.toUpperCase(),
      subjectExpertise,
    };
    console.log("Tutor Signup Data:", tutorData);
    fetch("http://localhost:8085/doubt-sharing-app/auth/register-teacher", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(tutorData),
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
      <h1>Tutor Signup</h1>
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
            value={userLanguage.toUpperCase()}
            onChange={handleUserLanguageChange}
            required
          />
        </label>
        <br />
        <label>
          Minimum Grade:
          <input
            type="text"
            value={min_grade.toUpperCase()}
            onChange={handleMinGradeChange}
            required
          />
        </label>
        <br />
        <label>
          Maximum Grade:
          <input
            type="text"
            value={max_grade.toUpperCase()}
            onChange={handleMaxGradeChange}
            required
          />
        </label>
        <br />
        <label>
          Subject Expertise:
          <input
            type="text"
            value={subjectExpertise.toUpperCase()}
            onChange={handleSubjectExpertiseChange}
            required
          />
        </label>
        <br />
        <button id="subBtn" type="submit">Sign Up</button>
        <br />
      </form>
    </div>
  );
};

export default TutorSignup;
