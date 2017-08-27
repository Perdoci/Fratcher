import React from "react";
import {Link} from "react-router-dom";
import User from "../util/User";

class Navigation extends React.Component {
    updateAuthentication() {
        // If we would store the authentication state in the component's state and reset the state,
        // we would not have to do this.
        this.forceUpdate();
    }
    render() {
        return (
            <nav className="navbar navbar-inverse navbar-fixed-top">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#navbar">
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <Link to="/" ><img src="assets/fratcher-logo.png" width="140" alt="fratcher-logo"></img></Link>
                    </div>
                    <div id="navbar" className="collapse navbar-collapse">

                        <ul className="nav navbar-nav">
                            {User.isAuthenticated() &&
                            <li><Link to="/">Play Match</Link></li>
                            }
                        </ul>

                        <ul className="nav navbar-nav">
                            {User.isAuthenticated() &&
                            < li > < Link to="/post/new">My Matches</Link></li>
                            }
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            { User.isNotAuthenticated() &&
                            <li><Link to="/login">Login</Link></li>
                            }
                            {
                                User.isAuthenticated() &&
                                <li><Link to="/login">
                                    {User.email}
                                </Link></li>
                            }
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            { User.isNotAuthenticated() &&
                            <li><Link to="/register">Register</Link></li>
                            }

                        </ul>

                    </div>
                </div>
            </nav>
        );
    }
}

export default Navigation;