import React from 'react';
import './App.css';
import AllRoutes from './Components/AllRoutes';
import Navbar from './Components/Navbar';

function App() {
  return (
    <div className="App">
      <h1>Welcome To Doubt Sharing App</h1>
      <Navbar />
      <br />
      <AllRoutes />
    </div>
  );
}

export default App;