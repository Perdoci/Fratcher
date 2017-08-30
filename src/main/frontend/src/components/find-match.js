import axios from "axios";
import React from "react";

class FindMatch extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            status: '',
            id:'',
            match: '',
        }
    }

    componentWillMount(){
        axios.get('/filter/status').then(({data}) => {
            if(data !== null){
                this.setState({
                    status: data.status,
                    id: data.id
                })
            }
            this.noStatus();
        });
        this.gotMatch()
    }

    noStatus(){
        if(this.state.status === undefined){
            return(
                <div>!!! No more statuses found. </div>
            );
        }
        return null;

    }


    handleLikeStatus() {
        var likeId= this.state.id;

        if(likeId !== undefined){
            axios.post('/filter/status/'+likeId+'/like').then(({data}) => {
                this.setState({
                    match: data.email
                })
                this.componentWillMount();
            });
        }
    }

    handleDislikeStatus() {
        var dislikeId= this.state.id;
        if(dislikeId !== undefined) {
            axios.post('/filter/status/' + dislikeId + '/dislike').then(() => {
                this.componentWillMount();
            });
        }
    }

    gotMatch(){
        if(this.state.match !== '' && this.state.match !== undefined){
            return (
                <div>You got a match with: {this.state.match}</div>
            );
        }
        return null;

    }

    handleClickTest() {
        axios.get('/filter/status').then(({data}) => {
            this.setState({
                status: data.status,
                id: data.owner.id
            })
        });


        console.log(this.state.id);
    }

    render() {
        return (
            <div>
              <div onClick={() => this.handleClickTest()}>{this.state.status} { this.noStatus()}</div>
                <br></br><br></br>
                <button type="button"  onClick={() => this.handleLikeStatus()}>Like</button>
                <button type="button"  onClick={() => this.handleDislikeStatus()}>Dislike</button>

                <br></br><br></br>
                <div>{this.gotMatch()}</div>
            </div>
        );
    }



}

export default  FindMatch;