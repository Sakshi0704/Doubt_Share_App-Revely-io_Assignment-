import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../CSS/Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const basicAuth = btoa(`${email}:${password}`);
    const url = "http://localhost:8085/doubt-sharing-app/auth/user/signIn";

    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Basic ${basicAuth}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const newToken = response.headers.get("Authorization");
console.log(newToken);
        // Save the token to local storage
        localStorage.setItem("token", newToken);

        return response.json();
      })
      .then((data) => {
        console.log(data);

        window.alert("Login successful!");
        if (data.userType === "ROLE_STUDENT") {

          window.location.href = "/student_data";
        }
        else {
          window.location.href = "/tutor_data";

        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <div className="login-container">
      <h1>Login</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <input type="email" value={email} onChange={handleEmailChange} required />
        </label>
        <br />
        <label>
          Password:
          <input type="password" value={password} onChange={handlePasswordChange} required />
        </label>
        <br />
        <button type="submit">Login</button>
      </form>
      <Link to="/signup">Sign Up</Link>
    </div>
  );
};

export default Login;
