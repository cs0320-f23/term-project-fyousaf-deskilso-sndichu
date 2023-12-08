import { useState } from "react";
import React, { ReactNode } from "react";
import { Link } from "react-router-dom";

interface RecommendationProps {}

export default function Recommendation(props: RecommendationProps) {
  return (
    // TODO (eventually): add in real recommendation data from our algorithm using an API call
    <div>
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
