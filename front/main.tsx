import React from "react";
import ReactDOM from "react-dom/client";
import App from "./src/App";
import "./index.css";
import googleclientID from "./src/private/googleclientID";
import { GoogleOAuthProvider } from "@react-oauth/google";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <GoogleOAuthProvider clientId={googleclientID}>
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </GoogleOAuthProvider>
);
