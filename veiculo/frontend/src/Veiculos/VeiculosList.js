import React, {Component} from 'react';
import axios from '../axios-config'
import {Link} from "react-router-dom";
import {withStyles} from '@material-ui/core';
import styles from "../styles";
import clsx from 'clsx';
import Divider from '@material-ui/core/Divider';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import InputBase from "@material-ui/core/InputBase";
import IconButton from '@material-ui/core/IconButton';

import Typography from "@material-ui/core/Typography";
import CardActions from "@material-ui/core/CardActions";
import Button from "@material-ui/core/Button";
import CardContent from "@material-ui/core/CardContent";
import Card from "@material-ui/core/Card";
import Switch from "@material-ui/core/Switch";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import EditIcon from '@material-ui/icons/Edit';
import SearchIcon from '@material-ui/icons/Search';
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';

class VeiculosList extends Component {

    constructor(props) {
        super(props);

        this.listarVeiculos = this.listarVeiculos.bind(this);
        this.salvarStatusVenda = this.salvarStatusVenda.bind(this);
        this.findByKeyword = this.findByKeyword.bind(this);

        this.state = {veiculos:[], open:true};
    }

    componentDidMount() {
        this.listarVeiculos();
    }

    listarVeiculos() {
        axios.get('/veiculos').then(response => {
            let veiculos = response.data;
            this.setState({veiculos});
        }).catch(e => {
            console.log(e);
        });

    }

    salvarStatusVenda(veiculo) {
        axios.patch(`/veiculos/${veiculo.id}`, {vendido: !veiculo.vendido}).then(response => {
            let veiculos = this.state.veiculos;
            veiculos.forEach(v => {
                if(v.id === veiculo.id) {
                    v.vendido = !veiculo.vendido;
                }
            })
            this.setState({veiculos})
        }).catch(e => {
            console.log(e);
        });

    }

    findByKeyword(e){
        let palavraChave = e.target.value;
        if (palavraChave && palavraChave.length > 2) {
            axios.get(`/veiculos/find/?palavraChave=${palavraChave}`).then(response => {
                let veiculos = response.data
                this.setState({veiculos})
            }).catch(e => {
                console.log(e);
            });
        }else if(palavraChave.length === 0){
            this.listarVeiculos();
        }
    }

    excluirVeiculo(id){
        if (id && id > 0) {
            axios.delete(`/veiculos/${id}`).then(response => {
                let veiculos = this.state.veiculos;
                veiculos = veiculos.filter(v => v.id !== id);
                this.setState({veiculos})
            }).catch(e => {
                console.log(e);
            });
        }
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
            <Container maxWidth="lg" className={classes.container}>
                <Grid container spacing={3}>

                    <Grid item xs={12} lg={12}>
                        <Paper className={clsx(classes.root,  classes.pesquisar)}>
                            <InputBase
                                className={classes.input}
                                placeholder="Pesquisar veiculos"
                                inputProps={{ 'aria-label': 'pesquisar veiculos' }}
                                onChange={this.findByKeyword}
                            />
                            <IconButton type="submit" className={classes.iconButton} aria-label="search">
                                <SearchIcon />
                            </IconButton>
                            <Divider className={classes.divider} orientation="vertical" />
                            <Link to={"/veiculo"} className={classes.link}>
                                <IconButton color="primary" className={classes.iconButton} aria-label="directions">
                                    <AddIcon aria-label={'Adicionar veiculo'} />
                                </IconButton>
                            </Link>
                        </Paper>
                    </Grid>
                    {!this.state.veiculos || this.state.veiculos.length === 0 ? 'Não foi localizado veículos' : ''}
                    {this.state.veiculos.map(veiculo =>
                        {
                            return (
                                <Grid item xs={12} md={4} lg={3} key={veiculo.id} >
                                        <Card className={classes.rootCard}>
                                            <CardContent>
                                                <Typography className={classes.titleCard} color="textSecondary" gutterBottom>
                                                    {veiculo.marca}
                                                </Typography>
                                                <Typography variant="h5" component="h2">
                                                    {veiculo.nome}
                                                </Typography>
                                                <Typography className={classes.pos} color="textSecondary">
                                                    Ano: {veiculo.ano}
                                                </Typography>
                                                <Typography variant="body2" component="p">
                                                    {veiculo.descricao}
                                                </Typography>
                                            </CardContent>
                                            <CardActions>
                                                <Link to={`/veiculo/${veiculo.id}`} className={classes.link}>
                                                    <Button size="small"><EditIcon color="primary" /></Button>
                                                </Link>
                                                <FormControlLabel
                                                    control={
                                                        <Switch
                                                            color="primary"
                                                            checked={veiculo.vendido}
                                                            onChange={() => {return this.salvarStatusVenda(veiculo)}}
                                                            inputProps={{ 'aria-label': 'vendido' }} />
                                                    }
                                                    label="Vendido"
                                                />
                                                <IconButton aria-label="excluir" color="secondary" onClick={() => {this.excluirVeiculo(veiculo.id)}}>
                                                    <CloseIcon />
                                                </IconButton>

                                            </CardActions>
                                        </Card>
                                </Grid>
                            )
                        }
                    )}
                </Grid>
            </Container>

        );
    }
}

export default withStyles(styles)(VeiculosList);
