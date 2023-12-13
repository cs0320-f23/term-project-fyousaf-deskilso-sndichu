import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import googleclientID from "./private/googleclientID";
import { useNavigate } from "react-router-dom";

function Initial() {
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
    <div>
      <div
        style={{
          textAlign: "center",
          marginTop: "50px",
          backgroundColor: "#b07e73",
          padding: "30px",
        }}
      >
        <h1>Login with your Brown email</h1>
        <GoogleLogin
          clientId={googleclientID}
          buttonText={"Login with Google"}
          onSuccess={responseGoogle}
          onFailure={responseGoogle}
          hostedDomain={"brown.edu"}
          cookiePolicy={"single_host_origin"}
        />
        <div />
        <div />
        {/*<button type="button" onClick={() => navigate("/form")}>
          {" "}
          Form
        </button>
        <button type="button" onClick={() => navigate("/recommendation")}>
          {" "}
          Recommendation
    </button> **/}
      </div>
    </div>
  );
}

export default Initial;
