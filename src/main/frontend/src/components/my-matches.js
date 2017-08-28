import axios from "axios";
import React from "react";
import {Link} from "react-router-dom";

class Match extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            matches: []
        }

        this.handleClick = this.handleClick.bind(this);
    }

    componentWillMount() {
        axios.get('/filter/match').then(({data}) => {
            this.setState({
                matches: data
            })
        });
    }


    handleClick(id) {

    }
    handleClickTest() {

    }

    renderPosts() {


        return this.state.matches.map((match => {

            return (
                    <li key={match.id} >
                        <Link to={'/match/chat/'+ match.id}>{match.email} </Link>></li>
            );
        }));

    }


    render() {
        return (
            <div className="component">
                <table className="table table-hover">
                    <thead>
                    <tr onClick={() => this.handleClickTest()}>
                        <th className="col-sm-2">Created at</th>

                    </tr>
                    </thead>
                    <tbody>
                    {this.renderPosts()}
                    </tbody>
                </table>
            </div>
        );
    }
}


export default Match;

