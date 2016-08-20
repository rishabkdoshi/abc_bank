  $(document).ready(function() {
    $('#datepicker').datepicker({
                    format: "dd/mm/yyyy",
                    maxDate: '0'
                });  

    $('#new_customer_details').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            first_name: {
                validators: {
                        stringLength: {
                        min: 2,
                    },
                        notEmpty: {
                        message: 'Please supply your first name'
                    }
                }
            },
             last_name: {
                validators: {
                     stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Please supply your last name'
                    }
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: 'Please select your gender'
                    }
                }
            },
            dob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your dob'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'The value is not a valid date'
                    }
                }
            },
            
            address: {
                validators: {
                     stringLength: {
                        min: 8,
                    },
                    notEmpty: {
                        message: 'Please supply your street address, it has to be atleast 8 characters'
                    }
                }
            },
            city: {
                validators: {
                     stringLength: {
                        min: 4,
                    },
                    notEmpty: {
                        message: 'Please supply your city, it has to be atleast 4 characters'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'Please select your state'
                    }
                }
            },
            zip: {
                validators: {

                    notEmpty: {
                        message: 'Please supply your zip code'
                    },
                    regexp: {
                        regexp: /^\d{6}$/,
                        message: 'The Indian zipcode must contain exactly 6 digits'
                    }
                }
            },email: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your email address'
                    },
                    emailAddress: {
                        message: 'Please supply a valid email address'
                    }
                }
            },
            phone: {
                validators: {
                    regexp: {
                        regexp: /^[789]\d{9}$/,
                        message: 'Example of a valid phone number 9876543210'
                    }
                }
            },
            fax: {
                stringLength: {
                    max: 15,
                },
                regexp: {
                    regexp: /[0-9 ]+/,
                    message: 'Fax can be max 15 characters,only numbers and spaces'
                }
            }
            }
        })
        .on('success.form.bv', function(e) {

            $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                $('#new_customer_details').data('bootstrapValidator').resetForm();
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

        });
});



// $('#new_customer_details').bootstrapValidator().on('submit', function (e) {

//   if (e.isDefaultPrevented()) {
//     // handle the invalid form...
//     console.log("ee");
//   } else {
//     // everything looks good!
//     var formData = JSON.stringify($("#new_customer_details").serializeArray());
//     console.log(formData);
//   }
// })