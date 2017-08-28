import axios from "axios";
import React from "react";
import User from "../util/User";
import {withCookies} from "react-cookie";


class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            pass: '',
            status: '',
            error: undefined
        };

        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleRegister = this.handleRegister.bind(this);
        this.handleStatusChange = this.handleStatusChange.bind(this);
        this.cookies = this.props.cookies;
    }
    handleEmailChange(event) {
        this.setState({email: event.target.value});
    }

    handlePasswordChange(event) {
        this.setState({pass: event.target.value});
    }

    handleStatusChange(event) {
        this.setState({status: event.target.value});
    }



    handleRegister(event) {
        event.preventDefault();
        axios.post('/register', this.state, {
            // We allow a status code of 401 (unauthorized). Otherwise it is interpreted as an error and we can't
            // check the HTTP status code.
            validateStatus: (status) => {
                return (status >= 200 && status < 300) || status == 401
            }
        })
            .then(({data, status}) => {
                switch (status) {
                    case 200:
                        User.setCookieCredentials(data);
                        this.setState({error: undefined});

                        this.cookies.set('auth', {
                            token: data.token,
                            user: User
                        }, {path: '/'});

                        this.props.history.push("/");
                        break;

                    case 401:
                        this.setState({error: true});
                        break;
                }
            });

    }

    render() {
        let registerComponent = null;
        if (User.isNotAuthenticated()) {
            registerComponent =
                <form onSubmit={this.handleRegister} className="form-horizontal">
                    <div className="form-group">
                        <label className="col-sm-2">
                            Email
                        </label>
                        <div className="col-sm-4">
                            <input type="text" className="form-control"
                                   autoFocus={true}
                                   value={this.state.email}
                                   onChange={this.handleEmailChange}/>
                        </div>
                    </div>

                    <div className="form-group">
                        <label className="col-sm-2">
                            Password
                        </label>
                        <div className="col-sm-4">
                            <input type="password" name="pass" className="form-control"
                                   value={this.state.pass}
                                   onChange={this.handlePasswordChange}/>
                        </div>
                    </div>

                    <div className="form-group">
                        <label className="col-sm-2">
                            Status
                        </label>
                        <div className="col-sm-4">
                            <input type="text" className="form-control"
                                   autoFocus={true}
                                   value={this.state.status}
                                   onChange={this.handleStatusChange}/>
                        </div>
                    </div>
                    <input type="submit" className="btn btn-success" value="Register"/>
                </form>
        } else {
            registerComponent =
                <div>
                    <button type="button" className="btn btn-danger" onClick={this.handleLogout}>Logout</button>
                </div>
        }

        return (
            <div className="component">
                {registerComponent}

                <p/>
                { this.state.error &&
                <div className="alert alert-danger">
                    Registration was not successful.
                </div>
                }
            </div>
        );
    }
}

export default withCookies(Register);