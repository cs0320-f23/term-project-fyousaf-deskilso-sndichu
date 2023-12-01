import { useState } from "react";
import React, { ReactNode } from "react";

interface RecommendationProps {}

export default function Recommendation(props: RecommendationProps) {
  return (
    // TODO (eventually): add in real recommendation data from our algorithm using an API call
    <div>
      <h2>Get a Personalized Recommendation!</h2>
      <button aria-label="Use this button to get a recommendation for the current weather, personalized and based on other user's data">
        Get Recommendation
      </button>
      <p>Your recommendation for today is the following: </p>
    </div>
  );
}
