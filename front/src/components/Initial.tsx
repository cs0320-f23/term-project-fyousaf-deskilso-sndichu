import React, { Dispatch, useState } from "react";
import Form from "./Form";
import Recommendation from "./Recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleCredentialResponse, GoogleLogin } from "@react-oauth/google";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import googleclientID from "../private/googleclientID";
import { useNavigate } from "react-router-dom";
import { GoogleOAuthProvider } from "@react-oauth/google";

interface initialProps {
  setUser: Dispatch<boolean>;
}

function Initial(props: initialProps) {
  const navigate = useNavigate();
  //const [user, setUser] = useState<any>(null);
  const responseGoogle = (response: GoogleCredentialResponse) => {
    console.log("here");
    // Handle the Google authentication response
    //console.log(response);
    //
    if (true) {
      // if (response.profileObj?.email.endsWith("@brown.edu"))
      console.log("Login successful");

      props.setUser(true);
      navigate("/home");
      // You may want to redirect or show an error message to the user
    }
    // You can set the user state or perform any other actions here
    else {
      onSuccess: (response: GoogleCredentialResponse) => {
        props.setUser(false);
        console.log("Login successful:", response);
      };
    }
  };
  // const responseGoogle2 = (response) => {
  //   console.log("failed");
  //   console.log(response);
  // };

  return (
    // TODO (eventually): add CSS to make the app look nice
    <div>
      <div
        data-testid="Google OAuth"
        style={{
          textAlign: "center",
          marginTop: "100px",
          backgroundColor: "#b07e73",
          padding: "100px",
        }}
      >
        <h1>Login with your Brown email</h1>
        <GoogleLogin
          //clientID={googleclientID}
          onSuccess={responseGoogle}
          onError={() => {
            console.log("failure");
          }}
          hosted_domain={"brown.edu"}
          //cookie_policy={"single_host_origin"}
        />
        <div />
        <div />
      </div>
    </div>
  );
}

export default Initial;
