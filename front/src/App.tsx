import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";
import Rating from "./Rating";

function App() {
  return (
    // TODO (eventually): add CSS to make the app look nice
    <div
      style={{
        textAlign: "center",
        marginTop: "50px",
        backgroundColor: "#b07e73",
        padding: "30px",
      }}
    >
      <h1>Welcome to our app!</h1>
      <Rating />
      <Form />
      <Recommendation />
    </div>
  );
}

export default App;
