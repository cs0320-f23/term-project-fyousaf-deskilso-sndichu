import React, { useState } from "react";
import { GoogleLogin, GoogleLoginProps } from "@react-oauth/google";
import googleclientID from "./private/googleclientID";

export default function LoginPage(props: GoogleLoginProps) {
  const responseMessage = (response) => {
    // Check if the user's email belongs to the ".brown.edu" domain
    if (response.profileObj?.email.endsWith("@brown.edu")) {
      console.log("Login successful:", response);
      // You can set the user state or perform any other actions here
    } else {
      console.log("Login failed: User is not from the '.brown.edu' domain");
      // You may want to redirect or show an error message to the user
    }
  };

  const errorMessage = (error) => {
    console.log("Login Failed:", error);
  };

  return (
    <div>
      <h2>React Google Login</h2>
      <br />
      <br />
      <GoogleLogin
        clientId={googleclientID}
        buttonText="Login with Google"
        onSuccess={responseMessage}
        onError={errorMessage}
        cookiePolicy={"single_host_origin"}
      />
    </div>
  );
}

/**import { useEffect, useState } from "react";
import {useGoogleLogin, GoogleLogin} from "@react-oauth/google";
import { googleLogout } from "@react-oauth/google";
import React from "react";
import googleclientID from "./private/googleclientID";
import { CredentialResponse } from "@react-oauth/google";

interface LoginProps {}

export default function loginPage(props: LoginProps) {
  const [user, setUser] = useState<any>(null);
  const [profile, setProfile] = useState<any>(null);

  const login = useGoogleLogin({
    //TODO: switch to auth flow at some point. implicit allows for bodging while for easy auth work.
    //flow: "implicit",
    
   
    onSuccess: (CredentialResponse) => {
      setUser(CredentialResponse);
      console.log(CredentialResponse);
    },
    onError: (error) => console.log("Login Failed:", error),
    hosted_domain: "brown.edu",
  });

  const logOut = () => {
    googleLogout();
    setProfile(null);
  };

  return (
    <div>
      <GoogleLogin
        clientId={googleclientID}
        buttonText="Login with Google"
        onSuccess={login}
        onFailure={logOut}
        cookiePolicy={"single_host_origin"}
      />
    </div>
  );
} **/
