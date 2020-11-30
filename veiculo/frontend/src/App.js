import React, {Component} from 'react';
import clsx from 'clsx';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import {withStyles} from '@material-ui/core';
import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';
import styles from "./styles";
import {BrowserRouter, Link, Route, Switch} from "react-router-dom";
import Veiculo from "./Veiculos/Veiculo";
import VeiculosList from "./Veiculos/VeiculosList";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {open:true};
    }


    render() {
        const {classes} = this.props;

        const handleDrawerOpen = () => {
            this.setState({open: true});
        };
        const handleDrawerClose = () => {
            this.setState({open: false});
        };

        const fixedHeightPaper = clsx(classes.paper, classes.fixedHeight);

        return (
            <div className={classes.root}>
                <CssBaseline />
                <AppBar position="absolute" className={clsx(classes.appBar,  classes.appBarShift)}>

                    <Toolbar className={classes.toolbar}>
                        <Link to={"/"} className={classes.link}>
                            <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                                Veículos
                                <IconButton color="inherit">
                                    <DirectionsCarIcon />
                                </IconButton>
                            </Typography>
                        </Link>

                    </Toolbar>
                </AppBar>
                <div className={classes.content}>
                    <CssBaseline />
                    <main className={classes.content}>
                        <div className={classes.appBarSpacer} />
                        <Switch>
                            <Route exact path={'/'} component={VeiculosList} />
                            <Route exact path={['/veiculo', '/veiculo/:id']} component={Veiculo} />
                        </Switch>
                    </main>
                </div>

            </div>
        );
    }
}

export default withStyles(styles)(App);
