import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import googleclientID from "./private/googleclientID";
import { useNavigate } from "react-router-dom";

function App() {
  const navigate = useNavigate();
  const responseGoogle = (response) => {
    // Handle the Google authentication response
    //console.log(response);
    //
    // TODO: Add a boolean UseState variable for isLoggedIn, then use
    // stackoverflow post info about making a private route for the routes
  };

  return (
    // TODO (eventually): add CSS to make the app look nice
    <div>
      <div
        style={{
          textAlign: "center",
          marginTop: "50px",
          backgroundColor: "#b07e73",
          padding: "30px",
        }}
      >
        {/* <h1>Welcome to our app!</h1>
      <Form />
      <Rating />
      <Recommendation /> */}

        {
          // TO DO: figure out google client ID for authorization with google cloud
        }
        <h1>Home Page</h1>
        <GoogleLogin
          clientId={googleclientID}
          buttonText="Login with Google"
          onSuccess={responseGoogle}
          onFailure={responseGoogle}
          cookiePolicy={"single_host_origin"}
        />
        <div />
        <div />
        <button type="button" onClick={() => navigate("/form")}>
          {" "}
          Form
        </button>
        <button type="button" onClick={() => navigate("/recommendation")}>
          {" "}
          Recommendation
        </button>
      </div>
    </div>
  );
}

export default App;
