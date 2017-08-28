import axios from "axios";
import React from "react";

class Chat extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            messages: [],
            send: ''
        }

        this.handleMessageChange = this.handleMessageChange.bind(this);
        this.addMessage = this.addMessage.bind(this);
    }

    handleMessageChange(event) {
        this.setState({send: event.target.value});
    }

    componentWillMount() {
        axios.get('/filter/match/' + this.state.id)
            .then(({data}) => {
                this.setState({
                    messages: data
                });
            });
    }

    renderMessages() {
        if(this.state.messages.length !== 0) {

            return this.state.messages.map((text => {

                return (

                        <p key={text.id}> {text.text}</p>

                );

            }));

        }
        return null;
    }

    addMessage() {
        axios.post('/filter/match/' + this.state.id + '/message',
            {
                text: this.state.send,
            }
        )
            .then(({data}) => {
                this.setState({
                    messages: data
                });
                this.componentWillMount();
            });
    }


    render() {
        return (
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>Messages</th>
                    </tr>
                    </thead>
                    <tbody>
                    <p>
                    {this.renderMessages()}

                    </p>
                    <div>
                        <input type="text"
                               autoFocus={true}
                               onChange={this.handleMessageChange}/>
                    </div>
                    <button type="button" onClick={this.addMessage}>Send</button>
                    </tbody>
                </table>

            </div>

        );
    }
}

export default Chat;