import React, { useState } from "react";
import Form from "./Form";
import Recommendation from "./Recommendation";
import Rating from "./Rating";
import ReactDOM from "react-dom";
import { GoogleLogin } from "react-google-login";
import { GoogleOAuthProvider } from "@react-oauth/google";
import { BrowserRouter, Routes, Route, Link, Navigate } from "react-router-dom";
import Home from "./Home";
import LoginPage from "./Login";
import googleclientID from "../private/googleclientID";
import Login from "./Login";
import Initial from "./Initial";
import AboutUs from "./AboutUs";
import "./styles/App.css";

function App() {
  const [user, setUser] = useState<boolean>(false);
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

  const ProtectedRoute = ({ user, redirectPath = "/", children }) => {
    if (!user) {
      return <Navigate to={redirectPath} replace />;
    }

    return children;
  };
  return (
    <BrowserRouter>
      <div style={{ backgroundColor: "lavenderblush", minHeight: "500vh" }}>
        <div
          style={{
            textAlign: "center",
            color: "black",
            fontFamily: "Times New Roman, Times, serif",
            marginTop: "0px",
            marginBottom: "10px",
            backgroundColor: "lavenderblush",
            padding: "10px",
          }}
        >
          <div
            style={{
              background: "lightsteelblue",
              padding: "5px",
            }}
          >
            <h1>Weather</h1>
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              backgroundColor: "lightsteelblue",
              marginLeft: "20px",
            }}
          ></div>
          <Routes>
            <Route path="/" element={<Initial setUser={setUser} />} />
            <Route
              path="/home"
              element={
                <ProtectedRoute user={user}>
                  <Home />
                </ProtectedRoute>
              }
            />
            <Route
              path="/recommendation"
              element={
                <ProtectedRoute user={user}>
                  <Recommendation />{" "}
                </ProtectedRoute>
              }
            />
            <Route
              path="/form"
              element={
                <ProtectedRoute user={user}>
                  <Form />
                </ProtectedRoute>
              }
            />
            <Route
              path="/rating"
              element={
                <ProtectedRoute user={user}>
                  <Rating />
                </ProtectedRoute>
              }
            />
            <Route
              path="/aboutus"
              element={
                <ProtectedRoute user={user}>
                  <AboutUs />
                </ProtectedRoute>
              }
            />
            <Route path="*" element={<p>There's nothing here: 404!</p>} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
