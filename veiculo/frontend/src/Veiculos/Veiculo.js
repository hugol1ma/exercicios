import React, { Component } from "react";
import axios from "../axios-config";
import styles from "../styles";

import { TextField, Button, Grid, withStyles } from "@material-ui/core";
import Container from "@material-ui/core/Container";
import FormControl from "@material-ui/core/FormControl";
import Switch from "@material-ui/core/Switch";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";



class Veiculo extends Component {

    constructor(props) {
        super(props);
        this.consultarVeiculo = this.consultarVeiculo.bind(this);
        this.validarSalvarVeiculo = this.validarSalvarVeiculo.bind(this);
        this.salvarVeiculo = this.salvarVeiculo.bind(this);
        this.alterarVeiculo = this.alterarVeiculo.bind(this);
        this.onChange = this.onChange.bind(this);
        this.onChangeSwitch = this.onChangeSwitch.bind(this);
        this.handleClose = this.handleClose.bind(this);

        let veiculo = {id: 0, nome: '', marca: '', ano: 0, descricao: '',vendido: false};
        this.state = {veiculo};
    }
    componentDidMount() {
        if(this.props && this.props.match.params.id){
            this.consultarVeiculo(this.props.match.params.id);
        }
    }

    consultarVeiculo(id) {
        axios.get(`/veiculos/${id}`).then(response => {
            let veiculo = response.data;
            this.setState({veiculo})
        }).catch(e => {
            console.log(e);
        });

    }

    validarSalvarVeiculo() {

        if (!this.state.veiculo.nome) {
            return false;
        }
        if (!this.state.veiculo.marca) {
            return false;
        }
        if (!this.state.veiculo.ano) {
            return false;
        }
        return true;
    }

    salvarVeiculo() {

        if(!this.state.veiculo.nome) {
            alert('Informe o nome do veiculo')
        }
        if(!this.state.veiculo.marca) {
            alert('Informe a marca do veiculo')
        }
        if(!this.state.veiculo.ano) {
            alert('Informe o ano do veiculo')
        }

        if(this.state.veiculo.id && this.state.veiculo.id > 0){
            axios.put(`/veiculos/${this.state.veiculo.id}`, this.state.veiculo).then(response => {
                alert("Veiculo salvo com sucesso");
                this.props.history.push('/');
            }).catch(e => {
                console.log(e);
            });
        }else {
            axios.post('/veiculos', this.state.veiculo).then(response => {
                alert("Veiculo salvo com sucesso");
                this.props.history.push('/');
            }).catch(e => {
                console.log(e);
            });
        }
    }

    alterarVeiculo() {

    }

    onChange(e){
        let veiculo = this.state.veiculo;
        veiculo[e.target.name] = e.target.value;
        this.setState({veiculo});
    }

    onChangeSwitch(e){
        let veiculo = this.state.veiculo;
        veiculo[e.target.name] = e.target.checked;
        this.setState({veiculo});
    }

    handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        this.setState({open:false});
    };




    render() {
        const { classes } = this.props;

        return (
            <Container maxWidth="sm" className={classes.container}>
                <Grid container spacing={5}>

                    <Grid item xs={12} lg={12}>
                        <form className={classes.form} noValidate autoComplete="off">

                            <Grid container spacing={0}
                                  direction="column"
                                  alignItems="center"
                                  justify="center">
                                <Grid item xs={12} lg={12}>
                                    <FormControlLabel
                                        control={
                                            <Switch
                                                name="vendido"
                                                color="primary"
                                                checked={this.state.veiculo.vendido}
                                                onChange={this.onChangeSwitch}
                                                inputProps={{ 'aria-label': 'vendido' }} />
                                        }
                                        label="Vendido"
                                    />
                                </Grid>
                            </Grid>
                            <Grid container spacing={4}>

                                <Grid item xs={12} lg={4}>
                                    <FormControl fullWidth>
                                        <TextField
                                            label="Veiculo"
                                            name="nome"
                                            onChange={this.onChange}
                                            value={this.state.veiculo.nome}
                                            required
                                        />
                                    </FormControl>
                                </Grid>
                                <Grid item xs={12} lg={4} >
                                    <FormControl fullWidth>
                                        <InputLabel htmlFor="age-native-simple">Marca</InputLabel>
                                        <Select
                                            native
                                            value={this.state.veiculo.marca}
                                            onChange={this.onChange}
                                            inputProps={{
                                                name: 'marca',
                                                id: 'marca',
                                            }}
                                        >
                                            <option aria-label="" value="" />
                                            <option value={'CHEVROLET'}>Chevrolet</option>
                                            <option value={'VOLKSWAGEN'}> Volkswagen</option>
                                            <option value={'FIAT'}>Fiat</option>
                                            <option value={'RENAULT'}>Renault</option>
                                            <option value={'FORD'}>Ford</option>
                                            <option value={'TOYOTA'}>Toyota</option>
                                            <option value={'HONDA'}>Honda</option>
                                            <option value={'HYUNDAI'}>Hyundai</option>
                                        </Select>
                                    </FormControl>
                                </Grid>
                                <Grid item xs={12} lg={4} >
                                    <FormControl fullWidth>
                                        <TextField
                                            label="Ano"
                                            name="ano"
                                            type="number"
                                            onChange={this.onChange}
                                            value={this.state.veiculo.ano}
                                            required
                                        />
                                    </FormControl>
                                </Grid>
                            </Grid>
                            <Grid container spacing={0}>
                                <Grid item xs={12} lg={12}>
                                    <FormControl fullWidth className={classes.textarea}>
                                        <TextField
                                            label="Descrição"
                                            name="descricao"
                                            onChange={this.onChange}
                                            value={this.state.veiculo.descricao}
                                            multiline
                                        />
                                    </FormControl>
                                </Grid>
                            </Grid>
                            <Grid container spacing={0}
                                  direction="column"
                                  alignItems="center"
                                  justify="center">
                                <Grid item xs={12} lg={12}>
                                    <Button
                                        color="primary"
                                        variant="contained"
                                        className={classes.buttonSave}
                                        disabled={!this.validarSalvarVeiculo()}
                                        onClick={this.salvarVeiculo}>
                                        Salvar
                                    </Button>
                                </Grid>
                            </Grid>
                        </form>
                    </Grid>
                </Grid>

            </Container>

        );
    }
}


export default withStyles(styles)(Veiculo);

