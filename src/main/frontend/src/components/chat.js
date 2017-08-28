import axios from "axios";
import React from "react";

class Chat extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            send:''
        }

        this.handleMessageChange = this.handleMessageChange.bind(this);
    }

    handleMessageChange(event) {
        this.setState({send: event.target.value});
    }

    componentWillMount() {
        axios.get('/filter/match/' + this.props.match.params.id)
            .then(({data}) => {
                this.setState({
                    messages: data
                });
            });
    }

    renderMatches() {

        return this.state.messages.map((text => {

            return (
                <li key={text.id}  > {match.text} </li>
            );
        }));

    }




    render() {
        return (
            <div >
                <table >
                    <thead>
                    <tr>
                        <th >Messages</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.renderMatches()}
                    <div >
                        <input type="text"
                               autoFocus={true}
                               onChange={this.handleMessageChange}/>
                    </div>
                    <button type="button"  onClick={this.addMessage}>Send</button>
                    </tbody>
                </table>
            </div>
        );
    }

}

export default Chat;