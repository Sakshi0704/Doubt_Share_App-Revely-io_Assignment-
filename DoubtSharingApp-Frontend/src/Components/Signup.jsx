import React, { useState } from "react";
import { Link } from "react-router-dom";
import StudentSignup from "./StudentSignup";
import TutorSignup from "./TutorSignup";
import "../CSS/Signup.css";

const Signup = () => {
  const [isStudentForm, setIsStudentForm] = useState(true);

  const handleToggleForm = () => {
    setIsStudentForm((prevIsStudentForm) => !prevIsStudentForm);
  };

  return (
    <div>
      {isStudentForm ? <StudentSignup /> : <TutorSignup />}
      <button onClick={handleToggleForm}>
        {isStudentForm ? "Switch to Tutor Signup" : "Switch to Student Signup"}
      </button>
      <br />
      <br />
      <Link id="link" to="/login">
        Already have an account? Login
      </Link>
    </div>
  );
};

export default Signup;
