import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../CSS/Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [token, setToken] = useState("");

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
        "Authorization": `Basic ${basicAuth}`, // Include Basic Auth header
      }
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const oldToken = response.headers.get("Authorization");

        // console.log("Token:", oldToken);


        setToken(oldToken)
        // console.log("newToken:", token);


        return response.json();
      })
      .then((data) => {
        console.log(data);
        // Handle the response data as needed
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
        <button type="submit">Login</button>
      </form>
      {/* <br /> */}
      <Link to="/signup">Sign Up</Link>
    </div>
  );
};

export default Login;
