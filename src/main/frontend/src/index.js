import Authentication from "./components/auth";
import Navigation from "./components/nav";
import Register from "./components/register";
import User from "./util/User";
import React from "react";
import {CookiesProvider} from "react-cookie";
import ReactDOM from "react-dom";
import {HashRouter as Router, Route, Switch} from "react-router-dom";

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
                    <Route path="/login"
                           render={(props) => (
                               <Authentication {...props} updateAuthentication={this.updateAuthentication}/> )}/>
                    <Route path="/register" component={Register}/>

                    {/*Post handling*/}


                    {/*<Route path="/post/new" component={PostCreate}/>*/}
                    {/*<Route path="/post/:id" component={PostDetail}/>*/}


                    {/*Default route*/}
                    {/*  <Route path="/" component={PostList}/>*/}
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