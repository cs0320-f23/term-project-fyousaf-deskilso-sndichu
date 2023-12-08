import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { GoogleOAuthProvider } from "@react-oauth/google";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "./Home";

function App() {
  const responseGoogle = (response) => {
    // Handle the Google authentication response
    console.log(response);
  };

  return (
    // TODO (eventually): add CSS to make the app look nice

    <BrowserRouter>
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/recommendation" element={<Recommendation />} />
          <Route path="/form" element={<Form />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
