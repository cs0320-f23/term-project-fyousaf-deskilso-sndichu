import React, { useState } from "react";
import Form from "./Form";
import Recommendation from "./Recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import googleclientID from "../private/googleclientID";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();
  const [user, setUser] = useState<any>(null);

  return (
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
          <button className="button" style={{ marginLeft: "10px" }}>
            Home
          </button>
        </Link>
        <Link to="/recommendation">
          <button style={{ marginLeft: "10px" }}>Recommendation</button>
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
