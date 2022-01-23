$(document).ready(function(){

	var base_url = $('#base_url').val();
							$('.nohidden').attr('hidden','hidden');

$('.divstat').attr('hidden','hidden');
							$('.divothers').attr('hidden','hidden');
							$('.object').attr('hidden','hidden');
							$('.divtopflop').attr('hidden','hidden');
	$('#generation').submit(function(event){
		event.preventDefault();
		var semaine = $('#semaine').val();
		var annee = $('#annee').val();
		var semaineprec = $('#semaine').val()-1;
		var anneeprec = $('#annee').val()-1;

		if(semaineprec==0){
			semaineprec = 52;
			var anneePas = anneeprec;
		}else{
			var anneePas = annee;
		}

		$.ajax({
			type : 'POST',
			url  : base_url+'c_stat/recupTop' ,
			data : {semaine : semaine, semaineprec : semaineprec, annee : annee, anneePas : anneePas},
			success : function(data){
				var tab = data.split('.');
				var tabCode = tab[0].split(';');
				var tabCaf1 = tab[1].split(';');
				var tabCaf2 = tab[2].split(';');
				$('#codeTop1').text(tabCode[0]);
				$('#codeTop2').text(tabCode[1]);
				$('#codeTop3').text(tabCode[2]);

				var value1 = (tabCaf2[0]/tabCaf1[0])-1;
				var acc1 = value1.toFixed(1);
				var accc1 = acc1.split('.');
				if(accc1[1] == 0){
					value1 = accc1[0];
				}else{
					value1 = acc1;
				}

				var value2 = (tabCaf2[1]/tabCaf1[1])-1;
				var acc2 = value2.toFixed(1);
				var accc2 = acc2.split('.');
				if(accc2[1] == 0){
					value2 = accc2[0];
				}else{
					value2 = acc2;
				}

				var value3 = (tabCaf2[2]/tabCaf1[2])-1;
				var acc3 = value3.toFixed(1);
				var accc3 = acc3.split('.');
				if(accc3[1] == 0){
					value3 = accc3[0];
				}else{
					value3 = acc3;
				}

				if(tabCaf2[0]-tabCaf1[0]>0){
					$('#diffTop1').text('+'+(tabCaf2[0]-tabCaf1[0]));
					$('#varTop1').text('+'+value1+'%');
				}else{
					$('#diffTop1').text(''+(tabCaf2[0]-tabCaf1[0]));
					$('#varTop1').text(''+value1+'%');
				}
				if(tabCaf2[1]-tabCaf1[1]>0){
					$('#diffTop2').text('+'+(tabCaf2[1]-tabCaf1[1]));
					$('#varTop2').text('+'+value2+'%');
				}else{
					$('#diffTop2').text(''+(tabCaf2[1]-tabCaf1[1]));
					$('#varTop2').text(''+value2+'%');
				}
				if(tabCaf2[2]-tabCaf1[2]>0){
					$('#diffTop3').text('+'+(tabCaf2[2]-tabCaf1[2]));
					$('#varTop3').text('+'+value3+'%');
				}else{
					$('#diffTop3').text(''+(tabCaf2[2]-tabCaf1[2]));
					$('#varTop3').text(''+value3+'%');
				}
			},
			error : function(data) {
				swal("Echec", "Operatiorn Echouee", "error");
			}
		});	

		$.ajax({
			type : 'POST',
			url  : base_url+'c_stat/recupFlop' ,
			data : {semaine : semaine, semaineprec : semaineprec, annee : annee, anneePas : anneePas},
			success : function(data){
				var tab = data.split('.');
				var tabCode = tab[0].split(';');
				var tabCaf1 = tab[1].split(';');
				var tabCaf2 = tab[2].split(';');
				$('#codeFlop1').text(tabCode[0]);
				$('#codeFlop2').text(tabCode[1]);
				$('#codeFlop3').text(tabCode[2]);

				var value1 = (tabCaf2[0]/tabCaf1[0])-1;
				var acc1 = value1.toFixed(1);
				var accc1 = acc1.split('.');
				if(accc1[1] == 0){
					value1 = accc1[0];
				}else{
					value1 = acc1;
				}

				var value2 = (tabCaf2[1]/tabCaf1[1])-1;
				var acc2 = value2.toFixed(1);
				var accc2 = acc2.split('.');
				if(accc2[1] == 0){
					value2 = accc2[0];
				}else{
					value2 = acc2;
				}

				var value3 = (tabCaf2[2]/tabCaf1[2])-1;
				var acc3 = value3.toFixed(1);
				var accc3 = acc3.split('.');
				if(accc3[1] == 0){
					value3 = accc3[0];
				}else{
					value3 = acc3;
				}

				if(tabCaf2[0]-tabCaf1[0]>0){
					$('#diffFlop1').text('+'+(tabCaf2[0]-tabCaf1[0]));
					$('#varFlop1').text('+'+value1+'%');
				}else{
					$('#diffFlop1').text(''+(tabCaf2[0]-tabCaf1[0]));
					$('#varFlop1').text(''+value1+'%');
				}
				if(tabCaf2[1]-tabCaf1[1]>0){
					$('#diffFlop2').text('+'+(tabCaf2[1]-tabCaf1[1]));
					$('#varFlop2').text('+'+value2+'%');
				}else{
					$('#diffFlop2').text(''+(tabCaf2[1]-tabCaf1[1]));
					$('#varFlop2').text(''+value2+'%');
				}
				if(tabCaf2[2]-tabCaf1[2]>0){
					$('#diffFlop3').text('+'+(tabCaf2[2]-tabCaf1[2]));
					$('#varFlop3').text('+'+value3+'%');
				}else{
					$('#diffFlop3').text(''+(tabCaf2[2]-tabCaf1[2]));
					$('#varFlop3').text(''+value3+'%');
				}
			},
			error : function(data) {
				swal("Echec", "Operatiorn Echouee", "error");
			}
		});	
		
		$.ajax({
			type : 'POST',
			url  : base_url+'c_stat/recupDonnees' ,
			data : {semaine : semaine, annee : annee},
			success : function(data){
				var tab = data.split('.');
				var tabCode = tab[0].split(';');
				var tabCaf = tab[1].split(';');
				
				var colorss = ['#E67E30','#79F8F8','#82C46C','#91283B','#F0C300','#884DA7','#5A5E6B','#568203','#007FFF','#FF5E4D','#960018','#7FFF00','#85530F','#01D758','#791CF8','#2E006C'];
    			var pieData = [];
    			var i = 1;
			    for (i = 0; i < tabCode.length; i++) {
			        pieData.push({data : tabCaf[i],color : colorss[i], label : tabCode[i]});
			    }
			    if($('#pie-chart')[0]){
			        $.plot('#pie-chart', pieData, {
			            series: {
			                pie: {
			                    show: true,
			                    radius : 1,
			                    stroke: { 
			                        width: 2,
			                    },
			                    label: {
									show: true,
									radius: 3/4,
					                background: { 
					                    opacity: 0.5,
					                    color: '#000'
					                }
								}
			                },
			            },
			            legend: {
			                container: '.flc-pie',
			                backgroundOpacity: 0.5,
			                noColumns: 0,
			                backgroundColor: "white",
			                lineWidth: 0
			            },
			            grid: {
			                hoverable: true,
			                clickable: true
			            },
			            tooltip: true,
			            tooltipOpts: {
			                content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
			                shifts: {
			                    x: 20,
			                    y: 0
			                },
			                defaultTheme: false,
			                cssClass: 'flot-tooltip'
			            },
			        });
			    }
			},
			error : function(data) {
				swal("Echec", "Operation Echouee", "error");
			}
		});	

		$.ajax({
			type : 'POST',
			url  : base_url+'c_stat/getInfosGraph' ,
			data : {semaine : semaine, annee : annee},
			success : function(data){
				var tab = data.split(',');
				$('#statsem').text(tab[0]);
				$('#statann').text(tab[1]);
			},
			error : function(data) {
				swal("Echec", "Operation Echouee", "error");
			}
		});	

		$.ajax({
			type : 'POST',
			url : base_url+'c_stat/getNbVilles',
			data : {bof : 1},
			success : function(data){
				var nbVilles = data;
				$.ajax({
					type : 'POST',
					url  : base_url+'c_stat/recupDonnees' ,
					data : {semaine : semaine, annee : annee},
					success : function(data){
						var tab = data.split('.');
						var tabCode = tab[0].split(';');
						var tabCaf = tab[1].split(';');
						if(tabCode==''||tabCode.length!=nbVilles){
							$('.nohidden').removeAttr('hidden');
							$('.afficherErreur').text('Il n\'y a pas de statistiques à génerer pour cette semaine à cause du manque de données!');
							$('.divstat').attr('hidden','hidden');
							$('.divothers').attr('hidden','hidden');
							$('.object').attr('hidden','hidden');
							$('.divtopflop').attr('hidden','hidden');
						}else{
							var d1 = [];
							var i = 1;
						    for (i = 1; i <= tabCaf.length; i++) {
						        d1.push([i, tabCaf[i-1]]);
						    }
						    $.ajax({
						    	type : 'POST',
								url  : base_url+'c_stat/recupDonnees' ,
								data : {semaine : semaineprec, annee : anneePas},
								success : function(data){
									var tabprec = data.split('.');
									var tabCodeprec = tabprec[0].split(';');
									var tabCafprec = tabprec[1].split(';');
									//alert(tabCaf);
									var d2 = [];
									var j = 1;
									if(tabCodeprec == ''||tabCodeprec.length!=nbVilles){

									}else{
										for (j = 1; j <= tabCafprec.length; j++) {
								        	d2.push([j, tabCafprec[j-1]]);
								    	}
									}
								    $.ajax({
								    	type : 'POST',
										url  : base_url+'c_stat/recupDonnees' ,
										data : {semaine : semaine, annee : anneeprec},
										success : function(data){
											var tabann = data.split('.');
											var tabCodeann= tabann[0].split(';');
											var tabCafann = tabann[1].split(';');
											//alert(tabCaf);
											var d3 = [];
											var k = 1;
											if(tabCodeann == ''||tabCodeann.length!=nbVilles){

											}else{
												for (k = 1; k <= tabCafann.length; k++) {
										        	d3.push([k, tabCafann[k-1]]);
										    	}
											}
										    
										    var barData = new Array();
										    var colo = [];
										    colo.push('#7E3300');
										    barData.push({
										            data : d1,
										            label: 'Sem. Actuelle',
										            bars : {
										                    show : true,
										                    barWidth : 0.2,
										                    order : 1,
										                    lineWidth: 0,
										                    fillColor: '#7E3300'
										            }
										    });

										    if(j==1){
										    	 
										    }else{
										    	colo.push('#FCDC12');
										    	barData.push({
										            data : d2,
										            label: 'Sem. Prec.',
										            bars : {
										                    show : true,
										                    barWidth : 0.2,
										                    order : 2,
										                    lineWidth: 0,
										                    fillColor: '#FCDC12'
										            }
										    	});
										    }

										    if(k==1){

										    }else{
										    	colo.push('red');
										    	barData.push({
										            data : d3,
										            label: 'Sem. Ann. Pass.',
										            bars : {
										                    show : true,
										                    barWidth : 0.2,
										                    order : 3,
										                    lineWidth: 0,
										                    fillColor: 'red'
										            }
										   		});
										    }
										    

											if ($('#bar-chart')[0]) {
												if(j==1){
													$('.divtopflop').attr('hidden','hidden');
												}else{
													$('.divtopflop').removeAttr('hidden');
												}
												$('.divstat').removeAttr('hidden');
												$('.divothers').removeAttr('hidden');
												$('.nohidden').attr('hidden','hidden');
												var d4 = [];
												var l = 1;
											    for (l = 1; l <= tabCode.length; l++) {
											        d4.push([l, tabCode[l-1]]);
											    }

							    				$.plot($("#bar-chart"), barData, {
							    					colors : colo,
										            grid : {
										                    borderWidth: 1,
										                    borderColor: '#eee',
										                    show : true,
										                    hoverable : true,
										                    clickable : true
										            },
										            
										            yaxis: {
										                tickColor: '#eee',
										                tickDecimals: 0,
										                font :{
										                    lineHeight: 13,
										                    style: "normal",
										                    color: "#9f9f9f",
										                },
										                shadowSize: 0
										            },
										            
										            xaxis: {
										            	
										                tickColor: '#fff',
										                tickDecimals: 0,
										                ticks : d4,
										                font :{
										                    lineHeight: 13,
										                    style: "normal",
										                    color: "#9f9f9f"
										                },
										                shadowSize: 0,
										            },
										    
										            legend:{
										                container: '.flc-bar',
										                backgroundOpacity: 0.5,
										                noColumns: 0,
										                backgroundColor: "white",
										                lineWidth: 0
										            }

							    				});
											}

										    if ($(".flot-chart")[0]) {
										        $(".flot-chart").bind("plothover", function (event, pos, item) {
										            if (item) {
										                var x = item.datapoint[0].toFixed(2),
										                    y = item.datapoint[1].toFixed(2);
										                $(".flot-tooltip").html(item.series.label + " of " + x + " = " + y).css({top: item.pageY+5, left: item.pageX+5}).show();
										            }
										            else {
										                $(".flot-tooltip").hide();
										            }
										        });
										        
										        $("<div class='flot-tooltip' class='chart-tooltip'></div>").appendTo("body");
										    }


										},
										error : function(data){
											swal("Echec", "Operation Echouee", "error");
										}
								    });
								},
								error : function(data){
									swal("Echec", "Operation Echouee", "error");
								}
						    });
							$.ajax({
								type : 'POST',
								url  : base_url+'c_stat/getVillesObj' ,
								data : {semaine : semaine, annee : annee},
								success : function(data){
									var tab = data.split('.');
									var tabCode = tab[0].split(';');
									var tabCaf = tab[1].split(';');
									var tabObj = tab[2].split(';');
									//$.ajaxSetup({async: false});
									
											for(o=0;o<nbVilles;o++){
												$('#ligne'+(o+1)).attr('hidden','hidden');
											}
											if(tabCode.length==1){
												$('.object').attr('hidden','hidden');
											}else{
												$('.object').removeAttr('hidden');
												for(i=0;i<tabCode.length;i++){
													$('#ligne'+(i+1)).attr('hidden','hidden');
													var value = ((tabCaf[i]-tabObj[i])/tabObj[i]);
													var acc = value.toFixed(1);
													var accc = acc.split('.');
													if(accc[1] == 0){
														value = accc[0];
													}else{
														value = acc;
													}
													$('#ligne'+(i+1)).removeAttr('hidden');
													$('#code'+(i+1)).text(tabCode[i]);
													$('#diff'+(i+1)).text('+'+(tabCaf[i]-tabObj[i]));
													$('#var'+(i+1)).text('+'+value+'%');
												}
											}
										
									//alert(tabCode.length);
									
									
								},
								error : function(data) {
									swal("Echec", "Operation Echouee", "error");
								}
							});	
											}
										},
										error : function(data) {
											swal("Echec", "Operation Echouee", "error");
										}
									});	
								},
								error : function(data){
									swal("Echec", "Operation Echouee", "error");
								}
							});

		
	});
       
});