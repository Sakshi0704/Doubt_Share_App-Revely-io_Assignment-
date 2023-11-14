import React from "react";
import { Link } from "react-router-dom"; 

const HomePage = () => {
  return (
    <div>
      
      <Link id="link" to="/login">
        Already have an account? Login
      </Link>
    </div>
  );
};

export default HomePage;
