import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Register from './Components/Register';
import Login from './Components/Login';


const App = () => {
    console.log('Register:', Register);
console.log('Login:', Login);

  return (
    <BrowserRouter>
      <nav style={{ marginBottom: 20 }}>
        <Link to="/register">Register</Link> | <Link to="/login">Login</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Register />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
