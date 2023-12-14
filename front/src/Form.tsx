import { useState } from "react";
import React, { ReactNode } from "react";
import { Formik } from "formik";
import { Link } from "react-router-dom";
import Modal from "react-modal";

interface FormProps {}

interface error {
  email: string;
}

export default function Form(props: FormProps) {
  const [modalIsOpen, setModalIsOpen] = useState(false);
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
      <Link to="/aboutus">
        <button style={{ marginLeft: "10px" }}>About Us</button>
      </Link>
      <h2>Weather Inputter</h2>
      <button onClick={() => setModalIsOpen(true)}>Open Form</button>

      <Modal
        isOpen={modalIsOpen}
        onRequestClose={() => setModalIsOpen(false)}
        style={{
          overlay: {
            backgroundColor: "rgba(0, 0, 0, 0.5)",
          },
          content: {
            backgroundColor: "#fff",
            padding: "20px",
            borderRadius: "8px",
            maxWidth: "400px",
            margin: "auto",
          },
        }}
      >
        <Formik
          initialValues={{ email: "", password: "" }}
          validate={(values) => {
            let errors: error = { email: "" };
            if (!values.email) {
              errors.email = "Required";
            } else if (
              !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
            ) {
              errors.email = "Invalid email address";
            }
            return errors;
          }}
          onSubmit={(values, { setSubmitting }) => {
            setTimeout(() => {
              alert(JSON.stringify(values, null, 2));
              setSubmitting(false);
              setModalIsOpen(false); // Close the modal after submission
            }, 400);
          }}
        >
          {({
            values,
            errors,
            touched,
            handleChange,
            handleBlur,
            handleSubmit,
            isSubmitting,
          }) => (
            <form onSubmit={handleSubmit}>
              {/*<label htmlFor="email">Email:</label>
              <input
                type="email"
                name="email"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.email}
              />
              {errors.email && touched.email && <div>{errors.email}</div>}
              <label htmlFor="password">Password:</label>
              <input
                type="password"
                name="password"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.password}
              />
              {errors.password && touched.password && (
                <div>{errors.password}</div>
              )}**/}
              <p>
                On a scale of 1 to 5, how cold do you feel compared to others?
              </p>
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

              <button type="submit" disabled={isSubmitting}>
                Submit
              </button>
            </form>
          )}
        </Formik>
      </Modal>

      <Link to={"/recommendation"}>
        <button type="button">View Recommendation</button>
      </Link>
    </div>
  );
}
