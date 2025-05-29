import { useState } from 'react';
import './App.css';

function App() {
  const [country, setCountry] = useState('');
  const [league, setLeague] = useState('');
  const [team, setTeam] = useState('');
  const [position, setPosition] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setPosition('');
    setError('');

    console.log('Sending request with:', { country, league, team });
    try {
      const response = await fetch(
        `http://localhost:8090/api/v1/team-position?country=${encodeURIComponent(country.trim())}&leagueId=${encodeURIComponent(league.trim())}&teamName=${encodeURIComponent(team.trim())}`
      );

      console.log('Raw response:', response);
      const text = await response.text();
      console.log('Response text:', text);


      if (response.ok) {
        setPosition(text);
      } else {
        setError(text);
      }
    } catch (err) {
      setError('Failed to fetch team position');
    }
  };

  return (
    <div className="app-background">
      <div className="app-container">
        <h1>Team League Position</h1>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="country">Country Name</label>
            <input
              id="country"
              type="text"
              value={country}
              onChange={(e) => setCountry(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="league">League ID</label>
            <input
              id="league"
              type="text"
              value={league}
              onChange={(e) => setLeague(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="team">Team Name</label>
            <input
              id="team"
              type="text"
              value={team}
              onChange={(e) => setTeam(e.target.value)}
              required
            />
          </div>

          <button type="submit">Get Position</button>
        </form>

        {position && <div className="result-message">{position}</div>}
        {error && <div className="error-message">{error}</div>}
      </div>
    </div>
  );
}

export default App;
