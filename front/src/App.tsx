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
import Initial from "./initial";
import AboutUs from "./AboutUs";

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
            color: "black",
            fontFamily: "Times New Roman, Times, serif",
            marginTop: "0px",
            backgroundColor: "brown",
            padding: "10px",
          }}
        >
          <h1>Weather</h1>
        </div>
        <div
          style={{ display: "flex", alignItems: "center", marginLeft: "20px" }}
        ></div>
        <Routes>
          <Route path="/" element={<Initial />} />
          <Route path="/home" element={<Home />} />
          <Route path="/recommendation" element={<Recommendation />} />
          <Route path="/form" element={<Form />} />
          <Route path="/rating" element={<Rating />} />
          <Route path="/aboutus" element={<AboutUs />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
