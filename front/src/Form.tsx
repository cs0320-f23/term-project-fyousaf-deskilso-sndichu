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

  return (
    <div style={{ backgroundColor: "#f0f0f0", padding: "30px" }}>
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
              <label htmlFor="email">Email:</label>
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
              )}
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
