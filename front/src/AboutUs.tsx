import { Navbar } from "react-bootstrap";
// import headshots from "./public"
import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

// Defines TeamMember interface which specifies properties of the team members
interface TeamMember {
  name: string;
  year: string;
  concentration: string;
  hometown: string;
  favoutfitprop: string;
  picture: string;
}

// team constant is an array of objects that fill the "TeamMember" interface
const team: TeamMember[] = [
  {
    name: "Fatima Yousaf",
    year: "2025",
    concentration: "Business-Economics & Computer Science",
    hometown: "Faisalabad, Pakistan",
    favoutfitprop: "Sweaters",
    picture: "https://i.imgur.com/G33XovG.jpg",
  },
  {
    name: "David Eskilson",
    year: "",
    concentration: "",
    hometown: "",
    favoutfitprop: "",
    picture: "",
  },
  {
    name: "Stanley Ndichu",
    year: "",
    concentration: "",
    hometown: "",
    favoutfitprop: "",
    picture: "",
  },
];
// React component that returns the JSX that describes the "About Us" page.
// has the NavBar component, a heading, and a "card-flex" div that contains the team members' info

// card-flex div maps over the "team" array using the "map" method.
// For each team member, a new "card" div is created that displays the member's info
// The "key" attribute is set to the member's name to improve rendering performance.
function AboutUs() {
  return (
    <div id="About-Us" aria-label="About Us">
      <div
        style={{
          textAlign: "center",
          marginTop: "20px",
          backgroundColor: "rosybrown",
          paddingTop: "10px",
          paddingBottom: "10px",
          minHeight: "80vh",
        }}
      >
        <h2>Meet Our Team</h2>
        <Navbar />
        <Link to="/home">
          <button style={{ marginLeft: "10px", marginBottom: "30px" }}>
            Home
          </button>
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
        <div
          className="card-flex"
          style={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
            gap: "10px",
          }}
        >
          {team.map((member) => (
            <div
              className="card"
              data-testid="team-member-card"
              key={member.name}
            >
              <img
                className="headshot"
                src={member.picture}
                alt={member.name}
                style={{ width: "150px", height: "150px" }}
              />
              <h2>{member.name}</h2>
              <p>Year: {member.year}</p>
              <p>Concentration: {member.concentration}</p>
              <p>Hometown: {member.hometown}</p>
              <p>Favorite thing to wear: {member.favoutfitprop}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default AboutUs;
