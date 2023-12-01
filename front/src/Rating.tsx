import { useState } from "react";
import React, { ReactNode } from "react";

interface RatingProps {}

export default function Rating(props: RatingProps) {
  const [rating, setRating] = useState<number | null>(null);

  const handleRatingChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedRating = parseInt(event.target.value, 10);
    setRating(selectedRating);
  };

  const ratingOptions = [
    { value: 1, label: "Not at all cold" },
    { value: 2, label: "Slightly cold" },
    { value: 3, label: "Moderately cold" },
    { value: 4, label: "Very cold" },
    { value: 5, label: "Extremely cold" },
  ];

  return (
    <div
      style={{
        textAlign: "center",
        marginTop: "50px",
        backgroundColor: "#b07e73",
        padding: "30px",
      }}
    >
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
