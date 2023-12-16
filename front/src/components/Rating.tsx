import { useState } from "react";
import React, { ReactNode } from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

interface RatingProps {}

export default function Rating(props: RatingProps) {
  const [rating, setRating] = useState<number | null>(null);

  const handleRatingChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedRating = parseInt(event.target.value, 10);
    setRating(selectedRating);
  };

  const ratingOptions = [
    { value: 1, label: "Much more" },
    { value: 2, label: "Slightly more" },
    { value: 3, label: "As cold as others" },
    { value: 4, label: "Slightly less" },
    { value: 5, label: "Extremely less" },
  ];

  return (
    <div
      style={{
        textAlign: "center",
        color: "black",
        fontFamily: "Times New Roman, Times, serif",
        marginTop: "50px",
        backgroundColor: "#b07e73",
        padding: "30px",
      }}
    >
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
      <h2>On a scale of 1 to 5, how cold do you feel compared to others?</h2>
      <form>
        <label>
          Select Rating:
          <select value={rating || ""} onChange={handleRatingChange}>
            <option value="" disabled>
              Select rating
            </option>
            {ratingOptions.map((option) => (
              <option key={option.value} value={option.value}>
                {`${option.value} - ${option.label}`}
              </option>
            ))}
          </select>
        </label>
      </form>
      {rating && <p>You rated: {rating}</p>}
    </div>
  );
}
