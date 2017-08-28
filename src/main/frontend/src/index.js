import Authentication from "./components/auth";
import Navigation from "./components/nav";
import Register from "./components/register";
import User from "./util/User";
import React from "react";
import {CookiesProvider} from "react-cookie";
import ReactDOM from "react-dom";
import {HashRouter as Router, Route, Switch} from "react-router-dom";
import FindMatch from "./components/find-match";
import Match from "./components/my-matches";
import Chat from "./components/chat";

class Root extends React.Component {
    constructor(props) {
        super(props);
        // Force initialization of the object.
        User.isAuthenticated();
        this.updateAuthentication = this.updateAuthentication.bind(this);
    }

// This is called whenever the authentication state of a user is changed by a component. Additionally,
// this is an example of intersibling communication with a common parent.
    updateAuthentication() {
        this.nav.updateAuthentication();
    }

    render() {
        return (
            <div>
                <Navigation ref={(component) => {
                    this.nav = component;
                }}/>
                <Switch>
                    {/*Authentication*/}
                    // See https://github.com/ReactTraining/react-router/issues/4627
                    <Route path="/user/login"
                           render={(props) => (
                               <Authentication {...props} updateAuthentication={this.updateAuthentication}/> )}/>
                    <Route path="/user/register" component={Register}/>

                    {/*Post handling*/}


                   <Route path="/status/show" component={FindMatch}/>
                   <Route path="/match/show" component={Match}/>

                     <Route path="/match/chat/:id" component={Chat}/>
                </Switch>
            </div>
        );
    }
}

ReactDOM.render(
    <CookiesProvider>
            <Router>
                <Root />
            </Router>

    </CookiesProvider>
    , document.getElementById('root'));