import { useState } from "react";
import React, { ReactNode } from "react";
import { Link } from "react-router-dom";

interface RecommendationProps {}

interface RecommendationResponse {
  recommendation: Array<Array<String>>;
}

function isRecommendationResponse(rjson: any): rjson is RecommendationResponse {
  if (!("recommendation" in rjson)) return false;
  return true;
}

export default function Recommendation(props: RecommendationProps) {
  const [recommendation, setRecommendation] = useState<string>(
    "Click button for recommendation"
  );
  function handleSubmit(mouseKeyEvent) {
    fetch(`http://localhost:3232/recommendation`)
      .then((r) => r.json())
      .then((response) => {
        if (isRecommendationResponse(response)) {
          setRecommendation(
            "One recommendation is " +
              response.recommendation.join(". Another recommendation is ")
          );
        } else {
          setRecommendation("There was an error finding the recommendation");
        }
      })
      .catch((e) =>
        setRecommendation(
          "There was an exception in finding the recommendation"
        )
      );
  }
  return (
    <div>
      <Link to="/home">
        <button style={{ marginLeft: "10px" }}>Home</button>
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
      <div
        style={{
          backgroundColor: "rosybrown",
          minHeight: "40vh",
          textAlign: "center",
          marginBottom: "20px",
          marginTop: "20px",
        }}
      >
        <h2
          style={{
            backgroundColor: "rosybrown",
            textAlign: "center",
            marginBottom: "20px",
            marginTop: "50px",
          }}
        >
          Get a Personalized Recommendation! Fill out form for your
          recommendation
        </h2>
        <Link to={"/form"}>
          <button type="button">Form</button>
        </Link>
        <button
          onClick={handleSubmit}
          aria-label="Use this button to get a recommendation for the current weather, personalized and based on other user's data"
        >
          Get Recommendation
        </button>
        <p>Your recommendation for today is the following: </p>
        <p>{recommendation}</p>
      </div>
    </div>
  );
}
