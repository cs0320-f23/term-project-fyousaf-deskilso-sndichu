import React, { useState } from "react";
import Form from "./form";
import Recommendation from "./recommendation";

function App() {
  return (
    // TODO (eventually): add CSS to make the app look nice
    <div>
      <h1>This is our app!</h1>
      <Form />
      <Recommendation />
    </div>
  );
}

export default App;
