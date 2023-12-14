import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import googleclientID from "./private/googleclientID";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();
  const [user, setUser] = useState<any>(null);
  const responseGoogle = (response) => {
    // Handle the Google authentication response
    //console.log(response);
    //
    // TODO: Add a boolean UseState variable for isLoggedIn, then use
    // stackoverflow post info about making a private route for the routes
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

      <h1>Home Page</h1>
      <h2>
        {" "}
        Welcome to Weather! Please click on a button to navigate to your desired
        tab
      </h2>
      <div>
        <Link to="/home">
          <button style={{ marginLeft: "10px" }}>Home</button>
        </Link>
        <Link to="/recommendation">
          <button style={{ marginLeft: "10px" }}>Recommendation</button>
        </Link>
        <Link to="/rating">
          <button style={{ marginLeft: "10px" }}> Rating</button>
        </Link>
        <Link to="/form">
          <button style={{ marginLeft: "10px" }}>Form</button>
        </Link>
        <Link to="/aboutus">
          <button style={{ marginLeft: "10px" }}>About Us</button>
        </Link>
        <div />
        <div />
      </div>
    </div>
  );
}

export default Home;
