import React, { useState } from "react";
import { GoogleLogin, GoogleLoginProps } from "@react-oauth/google";
import googleclientID from "./private/googleclientID";
import { useGoogleLogin } from "react-google-login";

export default function LoginPage(props: GoogleLoginProps) {
  const [user, setUser] = useState<any>(null);
  const responseMessage = (response) => {
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

    return (
      <div>
        <h1>React Google Login</h1>
        <br />
        <br />
        <GoogleLogin
          clientID={googleclientID}
          //buttonText="Login with Google"
          onSuccess={responseMessage}
          onError={responseMessage}
          hosted_domain="brown.edu"
          //cookiePolicy={"single_host_origin"}
        />
      </div>
    );
  };
}
