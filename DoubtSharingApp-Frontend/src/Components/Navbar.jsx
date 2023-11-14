import React from "react";
import { Link } from "react-router-dom";
import "../CSS/Navbar.css";
// import Logo from "../Images/Logo.png";
const Navbar = () => {
    return (
        <div className="navbar">
            {/* <img src={Logo} alt="" /> */}
            <Link to="/">Home</Link>
            <Link to="/student_login">Student</Link>
            <Link to="/tutor_login">Tutor</Link>
            <Link to="/login">Login</Link>
            {/* <Link to="/logout">Logout</Link> */}
        </div>
    );
};

export default Navbar;