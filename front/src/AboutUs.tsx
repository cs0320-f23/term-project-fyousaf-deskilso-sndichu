import { Navbar } from "react-bootstrap";
// import headshots from "./public"
import React from "react";

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
    concentration: "Business-Economics and Computer Science",
    hometown: "Faisalabad, Pakistan",
    favoutfitprop: "Sweaters",
    picture: "https://i.imgur.com/prDrI6W.png",
  },
  {
    name: "David Eskilson",
    year: "",
    concentration: "",
    hometown: "",
    favoutfitprop: "",
    picture: "https://i.imgur.com/2ARly61.png",
  },
  {
    name: "Stanley Ndichu",
    year: "",
    concentration: "",
    hometown: "",
    favoutfitprop: "",
    picture: "https://i.imgur.com/3BzaSfc.png",
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
      <Navbar />
      <h1>Meet Our Team</h1>
      <div className="card-flex">
        {team.map((member) => (
          <div
            className="card"
            data-testid="team-member-card"
            key={member.name}
          >
            <img className="headshot" src={member.picture} alt={member.name} />
            <h2>{member.name}</h2>
            <p>Year: {member.year}</p>
            <p>Concentration: {member.concentration}</p>
            <p>Hometown: {member.hometown}</p>
            <p>Favorite Fashion Piece: {member.favoutfitprop}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AboutUs;
