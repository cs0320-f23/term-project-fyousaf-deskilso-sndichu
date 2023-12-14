import { useState } from "react";
import React, { ReactNode } from "react";
import { Link } from "react-router-dom";

interface RecommendationProps {}

export default function Recommendation(props: RecommendationProps) {
  return (
    // TODO (eventually): add in real recommendation data from our algorithm using an API call
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
      <h2>
        Get a Personalized Recommendation! Fill out form for your recommendation
      </h2>
      <Link to={"/form"}>
        <button type="button">Form</button>
      </Link>
      <button aria-label="Use this button to get a recommendation for the current weather, personalized and based on other user's data">
        Get Recommendation
      </button>
      <p>Your recommendation for today is the following: </p>
    </div>
  );
}
