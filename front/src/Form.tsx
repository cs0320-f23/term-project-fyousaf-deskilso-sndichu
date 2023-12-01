import { useState } from "react";
import React, { ReactNode } from "react";
import { Formik } from "formik";

interface FormProps {}

interface error {
  email: string;
}

export default function Form(props: FormProps) {
  return (
    // TODO: add questions about normal feeling of coldness, perception of weather, successful outfits, etc.
    // Use our spec doc for what to ask about
    // formick docs: https://formik.org/docs/overview
    <div style={{ backgroundColor: "#f0f0f0", padding: "30px" }}>
      <h2>Weather Inputter</h2>
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
          /* and other goodies */
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
            {errors.email && touched.email && errors.email}
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              name="password"
              onChange={handleChange}
              onBlur={handleBlur}
              value={values.password}
            />
            {errors.password && touched.password && errors.password}
            <button type="submit" disabled={isSubmitting}>
              Submit
            </button>
          </form>
        )}
      </Formik>
    </div>
  );
}
