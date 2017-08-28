import axios from "axios";
import React from "react";

class FindMatch extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            status: '',
            id:''

        }
    }

    componentWillMount(){
        axios.get('/filter/status').then(({data}) => {
            this.setState({
                status: data.status,
                id: data.id
            })
        });

        console.log(this.state.id);
    }


    handleLikeStatus() {
        console.log(this.state.id);
        var likeId= this.state.id;
        axios.post('/filter/status/'+likeId+'/like').then(() => {
            this.componentWillMount();
        });


    }

    handleDislikeStatus() {
        console.log(this.state.id);
        var dislikeId= this.state.id;
        axios.post('/filter/status/'+dislikeId+'/dislike').then(() => {
            this.componentWillMount();
        });

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
              <div onClick={() => this.handleClickTest()}>{this.state.status} </div>
                <div>
                </div>
                <button type="button"  onClick={() => this.handleLikeStatus()}>Like</button>
                <button type="button"  onClick={() => this.handleDislikeStatus()}>Dislike</button>
            </div>
        );
    }



}

export default  FindMatch;