import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  const responseGoogle = (response) => {
    // Handle the Google authentication response
    console.log(response);
  };

  return (
    // TODO (eventually): add CSS to make the app look nice
    <div
      style={{
        textAlign: "center",
        marginTop: "50px",
        backgroundColor: "#b07e73",
        padding: "30px",
      }}
    >
      <h1>Welcome to our app!</h1>
      <Form />
      <Rating />
      <Recommendation />

      {
        // TO DO: figure out google client ID for authorization with google cloud
      }
      <h1>Home Page</h1>
      <GoogleLogin
        clientId="518580391302-kslsedv91v0cmvjcdubltrhj2navg7d7.apps.googleusercontent.com"
        buttonText="Login with Google"
        onSuccess={responseGoogle}
        onFailure={responseGoogle}
        cookiePolicy={"single_host_origin"}
      />
    </div>
  );
}

export default App;
