import axios from "axios";
import React from "react";

class Match extends React.Component {
    constructor(props) {
        super();
        this.state = {

            matches: []
        }

        this.handleClick = this.handleClick.bind(this);
    }

    componentWillMount() {

    }


    handleClick(id) {
        console.log("the id of the amtch clicked is" + id);

    }
    handleClickTest() {
        console.log("the id of the amtch clicked is");
        axios.get('/filter/match').then(({data}) => {
                this.setState({
                    matches: data
                })
            });
    }

    renderPosts() {

        console.log((""+this.state.matches).substring(0,this.state.matches.indexOf('}')+1 ));

        console.log(JSON.stringify(this.state.matches));


        return this.state.matches.map((match => {
            return (
                <tr key={match.id} onClick={() => this.handleClick(match.id)}>
                    <td>{match.id}</td>
                </tr>
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
                        <th className="col-sm-8">Title</th>
                        <th className="col-sm-2">Author</th>
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

