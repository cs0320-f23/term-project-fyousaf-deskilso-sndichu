import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { GoogleOAuthProvider } from "@react-oauth/google";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "./Home";
import LoginPage from "./Login";
import googleclientID from "./private/googleclientID";
import Login from "./Login";

function App() {
  const [user, setUser] = useState<any>(null);
  const responseGoogle = (response) => {
    // Handle the Google authentication response
    //console.log(response);
    //
    // TODO: Add a boolean UseState variable for isLoggedIn, then use
    // stackoverflow post info about making a private route for the routes
    // Check if the user's email belongs to the ".brown.edu" domain
    if (response.profileObj?.email.endsWith("@brown.edu")) {
      onSuccess: (response) => {
        setUser(response);
        console.log("Login successful:", response);
      };
      // You can set the user state or perform any other actions here
    } else {
      onError: (response) => {
        console.log("Login failed: Login with your Brown email", response);

        // You may want to redirect or show an error message to the user
      };
    }
  };

  return (
    // TODO (eventually): add CSS to make the app look nice

    <BrowserRouter>
      <div>
        <div
          style={{
            textAlign: "center",
            marginTop: "80px",
            backgroundColor: "purple",
            padding: "30px",
          }}
        >
          <h1>Weather</h1>
        </div>
        <div
          style={{ display: "flex", alignItems: "center", marginLeft: "20px" }}
        >
          <Link to="/">
            <button style={{ marginLeft: "10px" }}>Home</button>
          </Link>
          <Link to="/recommendation">
            <button style={{ marginLeft: "10px" }}>Recommendation</button>
          </Link>
          <Link to="/form">
            <button>Form</button>
          </Link>
        </div>
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
