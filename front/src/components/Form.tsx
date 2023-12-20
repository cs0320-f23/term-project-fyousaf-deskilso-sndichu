import { Dispatch, useState } from "react";
import React, { ReactNode } from "react";
import { Formik } from "formik";
import { Link } from "react-router-dom";
import Modal from "react-modal";

interface FormProps {
  submitted: boolean;
  setSubmitted: Dispatch<boolean>;
}

interface error {
  response: string;
  feedback: string;
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
        backgroundColor: "rosybrown",
        padding: "30px",
      }}
    >
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
          textAlign: "center",
          marginTop: "20px",
          backgroundColor: "rosybrown",
          paddingTop: "10px",
          paddingBottom: "10px",
          minHeight: "10vh",
        }}
      >
        <h2>Input your response</h2>
        <p
          style={{
            color: "white",
            fontFamily: "Times New Roman, Times, serif",
            textAlign: "center",
          }}
        >
          <strong>
            Note: You may submit the form only once. Please logout and login
            again to make another submission.
          </strong>
        </p>
        <button
          onClick={() => {
            if (props.submitted) {
            } else {
              setModalIsOpen(true);
            }
          }}
        >
          Open Form
        </button>

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
            initialValues={{
              recommend: "",
              removal: "",
              response: "",
              location: "",
              item: [],
            }}
            // validate={(values) => {
            //   let errors: error = { response: "", feedback: "" };

            //   if (!values.response) {
            //     errors.response = "*Required";
            //   }
            //   return errors;
            // }}
            onSubmit={(values, { setSubmitting }) => {
              console.log("in the onsubmit");
              alert(JSON.stringify(values, null, 5));
              setSubmitting(false);
              setModalIsOpen(false); // Close the modal after submission
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
                <p
                  style={{
                    color: "blue",
                    fontFamily: "Times New Roman, Times, serif",
                    textAlign: "center",
                  }}
                >
                  <strong>
                    You may submit the form only once. Please logout and login
                    again to make another submission.
                  </strong>
                </p>
                {/* Rating question */}
                <p
                  style={{
                    fontFamily: "Times New Roman, Times, serif",
                    marginTop: "20px",
                    marginBottom: "10px",
                  }}
                >
                  <strong>
                    On a scale of 1 to 5, how cold do you feel compared to
                    others?
                  </strong>
                </p>
                <label
                  style={{
                    fontFamily: "Times New Roman, Times, serif",
                    marginTop: "20px",
                    marginBottom: "20px",
                  }}
                >
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
                {rating && (
                  <p
                    style={{
                      fontFamily: "Times New Roman, Times, serif",
                      marginTop: "20px",
                      marginBottom: "20px",
                    }}
                  >
                    You rated: {rating}
                  </p>
                )}
                <div>
                  {/* Location question */}
                  <label
                    style={{
                      display: "block",
                      fontFamily: "Times New Roman, Times, serif",
                      marginTop: "20px",
                      marginBottom: "0px",
                      fontWeight: "bold",
                    }}
                  >
                    Current Location:
                  </label>
                  <div
                    style={{
                      display: "flex",
                      flexDirection: "column",
                      marginTop: "0px",
                      marginBottom: "20px",
                    }}
                  >
                    <label
                      htmlFor="location"
                      style={{ fontFamily: "Times New Roman, Times, serif" }}
                    >
                      <input
                        onChange={handleChange}
                        type="radio"
                        name="location"
                        value="inside"
                      />
                      Inside
                    </label>
                    <label
                      style={{ fontFamily: "Times New Roman, Times, serif" }}
                    >
                      <input
                        onChange={handleChange}
                        type="radio"
                        name="location"
                        value="outside"
                      />
                      Outside
                    </label>
                  </div>
                </div>

                <div>
                  {/* Clothing question */}
                  <label
                    style={{
                      fontFamily: "Times New Roman, Times, serif",
                      fontWeight: "bold",
                      marginTop: "20px",
                      marginBottom: "20px",
                    }}
                  >
                    What pieces of clothing are you wearing right now?
                  </label>
                  <div
                    style={{
                      display: "flex",
                      flexDirection: "column",
                      fontFamily: "Times New Roman, Times, serif",
                    }}
                  >
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="pants"
                      />
                      Pants/Sweatpants/Jeans/Trousers
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="sweater"
                      />
                      Sweater/Cardigan
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="shorts"
                      />
                      Shorts/Skirt
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="tank top"
                      />
                      Tank-Top/T-Shirt
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="coat"
                      />
                      Coat/Winter-Jacket/Puffer
                    </label>
                    <label>
                      <input
                        type="checkbox"
                        name="item"
                        value="head accessories"
                      />
                      Hat/Beanie/Cap/Ear-Muffs
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="hands"
                      />
                      Gloves/Mittens/Fuzzy-Socks/Scarf
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="winter shoes"
                      />
                      Winter-Boots/Fur-Lined-Shoes
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="summer shoes"
                      />
                      Sandals/Flip-Flops
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="sneakers"
                      />
                      Sneakers/Trainers/Crocs
                    </label>
                    <label>
                      <input
                        onChange={handleChange}
                        type="checkbox"
                        name="item"
                        value="Other"
                      />
                      Other
                    </label>
                  </div>
                </div>
                {/* Recommend question */}
                <div
                  style={{
                    fontFamily: "Times New Roman, Times, serif",
                    marginTop: "20px",
                    marginBottom: "20px",
                  }}
                ></div>
                <label
                  htmlFor="recommend"
                  style={{
                    fontFamily: "Times New Roman, Times, serif",
                    marginTop: "80px",
                    marginBottom: "20px",
                  }}
                >
                  <strong style={{ marginTop: "20px", display: "block" }}>
                    What would you recommend for an outdoor outfit today?
                  </strong>
                  <i>
                    *Please separate items with a '+' sign. e.g. "shirt + pant"
                  </i>
                  :
                </label>
                <input
                  type="text"
                  name="recommend"
                  onChange={handleChange}
                  onBlur={handleBlur}
                  value={values.recommend}
                  style={{
                    marginTop: "10px",
                    marginBottom: "20px",
                    width: "100%",
                    height: "100px",
                  }}
                />
                {errors.response && touched.response && (
                  <div
                    style={{
                      color: "red",
                      fontFamily: "Times New Roman, Times, serif",
                    }}
                  >
                    {errors.response}
                  </div>
                )}
                <div
                  style={{
                    fontFamily: "Times New Roman, Times, serif",
                    marginTop: "20px",
                    marginBottom: "20px",
                  }}
                ></div>
                {/* Removal question */}
                <label
                  htmlFor="removal"
                  style={{
                    fontFamily: "Times New Roman, Times, serif",
                    marginTop: "80px",
                    marginBottom: "20px",
                  }}
                >
                  <strong style={{ marginTop: "20px", display: "block" }}>
                    What would you remove or add to your current outfit?
                  </strong>
                </label>
                <input
                  type="text"
                  name="removal"
                  onChange={handleChange}
                  onBlur={handleBlur}
                  value={values.removal}
                  style={{
                    marginTop: "10px",
                    marginBottom: "20px",
                    width: "100%",
                    height: "100px",
                  }}
                />
                <div style={{ textAlign: "center" }}>
                  <button
                    type="submit"
                    //disabled={isSubmitting}
                    style={{ margin: "auto" }}
                    onClick={() => {
                      console.log(values);
                      props.setSubmitted(true);
                      let clothing: string = values.item.join("!");
                      fetch(
                        `http://localhost:3232/databasewrite?rating=${rating}&clothing=${clothing}`
                      );
                      setModalIsOpen(false);
                    }}
                  >
                    Submit
                  </button>
                </div>
              </form>
            )}
          </Formik>
        </Modal>
        <Link to={"/recommendation"}>
          <button type="button">View Recommendation</button>
        </Link>
      </div>
    </div>
  );
}
