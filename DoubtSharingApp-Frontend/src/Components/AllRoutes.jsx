import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./Login";
import Signup from "./Signup";
import HomePage from "./HomePage";
import StudentSignup from "./StudentSignup";
import TutorSignup from "./TutorSignup";
import StudentDataPage from "./StudentDataPage";
import TutorDataPage from "./TutorDataPage";

const AllRoutes = () => {
  return (
    <div>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/student_login" element={<StudentSignup />} />
        <Route path="/tutor_login" element={<TutorSignup />} />
        <Route path="/student_data" element={<StudentDataPage />} />
        <Route path="/tutor_data" element={<TutorDataPage />} />
      </Routes>
    </div>
  );
};
export default AllRoutes;