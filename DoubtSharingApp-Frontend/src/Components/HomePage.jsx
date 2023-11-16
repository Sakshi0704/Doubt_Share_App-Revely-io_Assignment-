// HomePage.js
import React from "react";
import HomeImage from "../Images/home2.jpg";
import "../CSS/HomePage.css";

const HomePage = () => {
  return (
    <div className="homepage-container">
      <div className="text-container">
        <h2>
          Unlocking Knowledge Together: Doubt Sharing App - Your Ultimate Platform for Seamless Doubt Sharing and Resolution! Join a Community of Inquisitive Minds, where Students Collaborate to Tackle Challenges and Clarify Concepts. Bridge the Gap in Learning with Real-time Doubt Assistance and Peer Support. Elevate Your Academic Journey with DoubtHub's Interactive and Inclusive Learning Environment. Say goodbye to uncertainties; let's learn and grow together!
        </h2>
      </div>

      <img className="homepage-image" src={HomeImage} alt="DoubtHub" />
    </div>
  );
};

export default HomePage;
